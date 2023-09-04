import React, { useEffect, useState } from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import { useNavigate } from 'react-router-dom'


const UserDashboard = () => {
  const [valid, setValid] = useState("");
  const result = localStorage.getItem("role");
  const IsLoggedIn = localStorage.getItem("IsLoggedIn");
  const navigate = useNavigate();

  useEffect(() => {
    if (result === "admin") {
      setValid("true");
    } else {
      setValid("false");
    }
    window.history.pushState(null, '', '/user-dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/user-dashboard');
    });
  },[])
  
  return (
    <>
    {(valid ==="false" && IsLoggedIn==="200") ? ( 
    <div>
        <Navbar/>
      <h1>Welcome to user dashboard!</h1>
    </div>
  ):(navigate('/error-page'))
  }
  </>
  )
}

export default UserDashboard
