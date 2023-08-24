import React,{useState} from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import './Form.css';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLoginClick = async (event) => {

    event.preventDefault();

    try {
        const response = await axios.post('http://localhost:8080/user/login', {email, password});
        console.log(response.data);
        if (response.status === 200) {
          console.log('');
          // Redirect upon successful login
          navigate('/dashboard'); // Change '/dashboard' to the desired URL
      } else {
          console.log('Invalid email or password.');
      }

    }catch(error){
        console.error(error.response ? error.response.data : 'An error occured');
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
              <span className="link-primary" style={{cursor:"pointer"}} onClick={redirectToRegister}>
                Sign Up
              </span>
            </div>
            <div className="form-group mt-3">
              <label>Email address</label>
              <input
                type="email"
                className="form-control mt-1"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div className="form-group mt-3">
              <label>Password</label>
              <input
                type="password"
                className="form-control mt-1"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="d-grid gap-2 mt-3">
              <button type="submit"  className="btn btn-primary">
                Submit
              </button>

              

              {/* <button type="submit" onClick={redirectToRegister} className="btn btn-primary">
                Register
              </button> */}
            </div>
          </div>
        </form>
      </div>  
    );
}

export default LoginForm
