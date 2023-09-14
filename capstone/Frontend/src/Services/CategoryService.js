import axios from 'axios';

const CATEGORY_BASE_URL = 'http://localhost:8080/categories';

class CategoryService{

    addCategory(Category){
        return axios.post(CATEGORY_BASE_URL, Category);
    }

    getAllCategories(){
        return axios.get(CATEGORY_BASE_URL);
    }

    getCategoryById(id){
        return axios.get(CATEGORY_BASE_URL+'/'+id);
    }

    updateCategory(id, Category){
        return axios.put(CATEGORY_BASE_URL+'/'+id,Category);
    }

    deleteCategory(id){
        return axios.delete(CATEGORY_BASE_URL+'/'+id);
    } 

    getQuizzesByCategory(id){
        return axios.get(CATEGORY_BASE_URL+'/'+id+'/'+"quizzes");
    }
}

export default new CategoryService();