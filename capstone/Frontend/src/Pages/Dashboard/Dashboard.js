import React,{useEffect, useState} from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import './Dashboard.css'
import welcomeImg from  '../../Assests/images/welcome-img.png';
import usersImg from '../../Assests/images/icon-users.png';
import ErrorPage from '../ErrorPage/ErrorPage'
import UserService from '../../Services/UserService';
import PageHeader from '../../Components/Header/PageHeader';

const Dashboard = () => {
  const userName = localStorage.getItem("userName");
  const [numOfUsers, setNumOfUsers] = useState(0);

  useEffect(() => {
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
    getAllUsers();
  }, []);

  const getAllUsers = () => {
    UserService.getAllUsers()
    .then((response) => {
      setNumOfUsers(response.data.length);
    })
    .catch((error) => {
    });
  }

  const userRole = localStorage.getItem('role');
  if (userRole !== 'admin') {
    return (
        <ErrorPage/>
      );
    }

  return (
    <div className='dashboard-container'>
      <Navbar/>
      <PageHeader className="dashboard-header" heading="ADMIN DASHBOARD"
          displayButton="" onClick="" name="" />
      <div className='dashboard-content'>
          <div className='welcome-box'>
              <h1>Welcome, {userName}</h1>
              <div>
                <img className='welcome-img' src={welcomeImg} alt="img"/>
              </div>
          </div>
          <div className='user-profile-box'>
              <div>
                <img className='users-img' src={usersImg} alt="img"/>
              </div>
            <h2>User Profiles: {numOfUsers}</h2>
          </div>
      </div>
      
    </div>
  )
}

export default Dashboard;