import axios from 'axios';

const CATEGORY_BASE_URL = 'http://localhost:8080/quiz';

class CategoryService{

    addQuiz(Quiz){
        return axios.post(CATEGORY_BASE_URL, Quiz);
    }

    getAllQuizzes(){
        return axios.get(CATEGORY_BASE_URL);
    }

    getQuizById(id){
        return axios.get(CATEGORY_BASE_URL+'/'+id);
    }

    updateQuiz(id, Quiz){
        return axios.put(CATEGORY_BASE_URL+'/'+id,Quiz);
    }

    deleteQuiz(id){
        return axios.delete(CATEGORY_BASE_URL+'/'+id);
    } 
}

export default new CategoryService();