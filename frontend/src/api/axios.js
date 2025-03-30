import axios from 'axios';

const instance = axios.create({
    baseURL: '/api', // nginx reverse proxy 환경에서 사용. React는 localhost:5173, Spring은 docker 내에서 작동
    headers: {
        'Content-Type': 'application/json',
    },
});

instance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
});

export default instance;