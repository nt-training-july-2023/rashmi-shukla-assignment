import axios from 'axios';

const USER_BASE_URL = "http://localhost:8080/users";

class UserService {

    // register(User){
    //     return axios.post(USER_BASE_URL+"/"+register, User);
    // }

    getAllUsers(){
        return axios.get(USER_BASE_URL);
    }

    // login(User){
    //     return axios.get(USER_BASE_URL+'/'+login,User);
    // }

}

export default new UserService();