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
    const [errors, setErrors] = useState({});
    const navigate = useNavigate(); 
    
    const handleSubmit=async(e) =>{
      e.preventDefault()
      const validattionErrors ={}
      if(firstName === ""){
        validattionErrors.firstName = "*required"
      }
      if(email === ""){
        validattionErrors.email = '*required'
      }else if(!/^[a-zA-Z0-9]+@nucleusteq\.com$/.test(email)){
        validattionErrors.email = "*email should end with @nucleusteq.com"
      }
      if(phoneNumber === ''){
        validattionErrors.phoneNumber = '*required'
      }else if(!/^([0-9]){10}$/.test(phoneNumber)){
        validattionErrors.phoneNumber = "*phone number should be of 10 digits"
      }
      if(password === ""){
        validattionErrors.password = "*required"
      }

      if(Object.keys(validattionErrors).length === 0){
        try{
          const response = await axios.post('http://localhost:8080/users/register', { firstName, lastName, phoneNumber, email, password });
          console.log(response.data);

          if(response.status===200){
            navigate("/")
          }
        }catch(error){
            validattionErrors.submit=error.response.data.message
            setErrors(validattionErrors)
            console.error(error.response ? error.response.data: 'An error occurred');
        } 
      }
      else{
        setErrors(validattionErrors)
      }
    }

    const redirectToLogin = () => { 
        navigate('/')
      }

  return (
    <div className="Auth-form-container">
    <form className="Auth-form" onSubmit={handleSubmit}>
      <div className="Auth-form-content">
        <h3 className="Auth-form-title">Sign Up</h3>
        <div className="text-center">
          Already registered?{" "}
          <span className="redirect-link" style={{ fontSize:15}} onClick={redirectToLogin}>
            Login
          </span>
        </div>

        <div className="form-group">
          <label>First Name</label>
          <input
            type="text"
            className="form-input"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
        
          />
          {errors.firstName && <span>{errors.firstName}</span>}
        </div>

        <div className="form-group">
          <label>Last Name</label>
          <input
            type="text"
            className="form-input"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            
          />
        </div>

        <div className="form-group">
          <label>Phone Number</label>
          <input
            type="phone"
            className="form-input"
            value={phoneNumber || ''}
            onChange={(e) => setPhoneNumber(e.target.value)}
            
          />
          {errors.phoneNumber && <span>{errors.phoneNumber}</span>}
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
        {errors.submit && <span>{errors.submit}</span>}
        <div className="button-group">
          <button type="submit"  className="button">
            Submit
          </button>
        </div>
        
      </div>
    </form>
  </div>
  )
}

export default RegistrationForm
