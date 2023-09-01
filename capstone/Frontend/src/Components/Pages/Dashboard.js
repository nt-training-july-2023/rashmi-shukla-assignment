import React,{useEffect} from 'react'
import Navbar from './Navbar/Navbar'
import './Dashboard.css'

const Dashboard = () => {

  useEffect(() => {
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
  }, []);

  return (
    <div>
      <Navbar/>
      <h1>Welcome to the admin dashboard!</h1>
    </div>
  )
}

export default Dashboard
