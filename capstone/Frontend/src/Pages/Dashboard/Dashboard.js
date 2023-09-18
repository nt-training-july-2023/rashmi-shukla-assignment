import React,{useEffect} from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import './Dashboard.css'
import welcomeImg from  './welcome-img.png';
import ErrorPage from '../ErrorPage/ErrorPage'

const Dashboard = () => {

  useEffect(() => {
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
  }, []);

  const userRole = localStorage.getItem('role');
  if (userRole !== 'admin') {
    return (
        <ErrorPage/>
      );
    }

  return (
    <div className='dashboard-container'>
      <Navbar/>
      <div className='dashboard-info'>
          <h1>ADMIN DASHBOARD</h1>
      </div>
      <div className='dashboard-content'>
          <div className='welcome-box'>
              <h1>Welcome, Dev</h1>
              <div>
              <img src={welcomeImg} alt="img"/>
              </div>
          </div>
          <div className='user-profile-box'>
            User Profiles: 10
          </div>
      </div>
      
    </div>
  )
}

export default Dashboard;