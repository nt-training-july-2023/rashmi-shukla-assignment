import React,{useEffect, useState} from 'react'
import Navbar from '../../Components/Navbar/Navbar'  //.. represents parent dir
import './Dashboard.css'
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {

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
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
  }, []);

  return (
    <>
    {(valid === "true"   && IsLoggedIn==="200" )? (
    <div>
      <Navbar/>
      <h1>Welcome to the admin dashboard!</h1>
    </div>): (navigate("/error-page"))}
    </>
  )
}

export default Dashboard
