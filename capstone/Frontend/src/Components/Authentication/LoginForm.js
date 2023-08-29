import React,{useState} from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import './Form.css';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] =useState({});
  const navigate = useNavigate();

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
          if (response.status === 200 && response.data==='user') {
            // Redirect upon successful login
            navigate('/dashboard'); // Change '/dashboard' to the desired URL
          }
          else if (response.status === 200 && response.data==='admin') {
            navigate('/dashboard'); 
          }
          localStorage.setItem("IsLoggedIn", response.status)
          localStorage.setItem("role",response.data)
        }catch(error){
            const submitError = error.response.data.message;
            validattionErrors.display= submitError;
            setErrors(validattionErrors);
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
    <div className="Auth-form-container">
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
            {errors.display && <span>{errors.display}</span>}
            <div className="button-group">
              <button type="submit"  className="button">
                Submit
              </button>

            </div>
          </div>
        </form>
      </div>  
    );
}

export default LoginForm