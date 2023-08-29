import React,{useEffect} from 'react'
import { useNavigate,  } from 'react-router-dom'


const Dashboard = () => {

  useEffect(() => {
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
  }, []);

  const navigate = useNavigate();
  const handleLogout = () =>{
    localStorage.removeItem("IsLoggedIn")
    localStorage.removeItem("role")
    navigate('/')
  }
  return (
    <div>
      ADMIN DASHBOARD!
      <button onClick={handleLogout}>Logout</button>
      <button onClick={()=>navigate('/ListCategory')}>Category</button>
    </div>
  )
}

export default Dashboard
