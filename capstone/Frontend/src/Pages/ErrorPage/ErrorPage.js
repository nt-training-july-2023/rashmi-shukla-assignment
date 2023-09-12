import React, { useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import './ErrorPage.css'

const ErrorPage = () => {
  const navigate = useNavigate();

  const [valid, setValid] = useState("");
  const result = localStorage.getItem("role");
  const IsLoggedIn = localStorage.getItem("IsLoggedIn");

  useEffect(() => {
    if (result === "admin") {
      setValid("true");
    } else {
      setValid("false");
    }
  },[]);

  const handleNavigate = () =>{
    if(valid==="true" && IsLoggedIn==="200"){
      navigate('/dashboard')
    }
    else if(valid==="false" && IsLoggedIn==="200"){
      navigate("/user-dashboard")
    }else{
      navigate("/")
    }
  }

  return (
    <div className='pageContainer'>
      <div className='contentContainer'>
        <h1>Unauthorized Access</h1>
        <p>Oops! The page you are looking for is unaccessible.</p>
        <button onClick = {handleNavigate}>
          Go back to the homepage
        </button>
      </div>
    </div>
  )
}

export default ErrorPage
