import React,{useState} from 'react'
import axios from 'axios'
import Swal from 'sweetalert2';
import loginImg from './bg.png';
import { useNavigate } from 'react-router-dom'
import Dashboard from '../Dashboard/Dashboard';
import UserDashboard from '../Dashboard/UserDashboard';
import './Form.css';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] =useState({});
  const navigate = useNavigate();

  const IsLoggedIn = localStorage.getItem("IsLoggedIn");

  const handleLoginClick = async (event) => {

    event.preventDefault();

    
    const validattionErrors ={}
      if(email === ""){
        validattionErrors.email = '*required'
      }
      if(password === ""){
        validattionErrors.password = "*required"
      }



      if(Object.keys(validattionErrors).length === 0){
        try {
          const response = await axios.post('http://localhost:8080/users/login', {email, password});
          console.log(response.data);
          Swal.fire({
            title: "Success",
            text: "Login Successful",
            icon: "success",
            timer:2000,
            showConfirmButton:false
          });
          if (response.status === 200 && response.data==='user') {
            // Redirect upon successful login
            navigate('/user-dashboard');
            // navigate('/private')
          }
          else if (response.status === 200 && response.data==='admin') {
            navigate('/dashboard'); 
          }
          localStorage.setItem("IsLoggedIn", response.status)
          localStorage.setItem("role",response.data)
        }catch(error){
            const submitError = error.response.data.message;
            Swal.fire({
              title: "Error",
              text: `${submitError}`,
              icon: "error",
              confirmButtonText: "Retry",
              confirmButtonColor:"red"
            });
            setEmail('');
            setPassword('');
            console.error(error.response ? error.response.data.message : 'An error occured');
        }
      }
      else{
      setErrors(validattionErrors);
      }
      
  };

  const redirectToRegister = () => { 
    navigate('/register')
  }

  return (
    <>
    <div className="Auth-form-container">
        {/* <div className="Auth-image">
        <img src={loginImg} alt="Login" />
        </div> */}
        <form className="Auth-form" onSubmit={handleLoginClick}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Login</h3>
            <div className="text-center"> 
              Not registered yet?{" "}
              <span className="redirect-link" style={{fontSize:15}} onClick={redirectToRegister}>
                Sign Up
              </span>
            </div>
            <div className="form-group">
              <label>Email address</label>
              <input
                type="email"
                className="form-input"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              {errors.email && <span>{errors.email}</span>}
            </div>

            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-input"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              {errors.password && <span>{errors.password}</span>}
            </div>

            <div className="button-group">
              <button type="submit"  className="button">
                Login
              </button>

            </div>
          </div>
        </form>
      </div>  </>
    );
}

export default LoginForm
