import React, { useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';

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
    <div>
      <h1>Path not found</h1>
      <button onClick = {handleNavigate}>
        GO BACK!
      </button>
    </div>
  )
}

export default ErrorPage
