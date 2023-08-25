import axios from 'axios';

const CATEGORY_BASE_URL = 'http://localhost:8080/categories';

class CategoryService{

    addCategory(){
        return axios.post(CATEGORY_BASE_URL);
    }

    getAllCategories(){
        return axios.get(CATEGORY_BASE_URL);
    }

    getCategoryById(id){
        return axios.get(CATEGORY_BASE_URL+'/'+id);
    }

    updateCategory(id){
        return axios.put(CATEGORY_BASE_URL+'/'+id);
    }

    deleteCategory(id){
        return axios.delete(CATEGORY_BASE_URL+'/'+id);
    }

   

}