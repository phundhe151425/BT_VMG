import axios from 'axios'

const url = 'http://localhost:8080/api/blogs'

class BlogService{
    getBlogs(){
        return axios.get(url);
    }
}

export  default new BlogService();
