import React from 'react';
import { useState } from 'react';
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import './Form.css';

const RegistrationForm = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState();
    const [email, setEmail ] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); 

    const handleRegisterClick =async(event)=>{
        event.preventDefault();

        try{
            const response = await axios.post('http://localhost:8080/user/register', { firstName, lastName, phoneNumber, email, password });
            console.log(response.data);
        }catch(error){
            console.error(error.response ? error.response.data: 'An error occurred');
        } 
    }

    const redirectToLogin = () => { 
        navigate('/')
      }

  return (
    <div className="Auth-form-container">
    <form className="Auth-form" onSubmit={handleRegisterClick}>
      <div className="Auth-form-content">
        <h3 className="Auth-form-title">Sign Up</h3>
        <div className="text-center">
          Already registered?{" "}
          <span className="link-primary" style={{cursor:"pointer"}} onClick={redirectToLogin}>
            Login
          </span>
        </div>
        <div className="form-group mt-3">
          <label>First Name</label>
          <input
            type="text"
            className="form-control mt-1"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </div>
        <div className="form-group mt-3">
          <label>Last Name</label>
          <input
            type="text"
            className="form-control mt-1"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </div>
        <div className="form-group mt-3">
          <label>Phone Number</label>
          <input
            type="text"
            className="form-control mt-1"
            value={phoneNumber || ''}
            onChange={(e) => setPhoneNumber(e.target.value)}
            required
          />
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
        </div>
        
      </div>
    </form>
  </div>
  )
}

export default RegistrationForm
