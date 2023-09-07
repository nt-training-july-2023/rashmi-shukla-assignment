import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom"
import LoginForm from './Pages/Authentication/LoginForm';
import RegistrationForm from './Pages/Authentication/RegistrationForm';
import Dashboard from './Pages/Dashboard/Dashboard';
import ListCategory from './Pages/Category/ListCategory';
import AddCategory from './Pages/Category/AddCategory';
import UserDashboard from './Pages/Dashboard/UserDashboard';
import ErrorPage from './Pages/ErrorPage/ErrorPage';

function App() {

  return (
     <Router>
        <Routes>
          <Route exact path='/' Component={LoginForm}></Route>
          <Route exact path='/register' Component={ RegistrationForm}></Route>
          <Route exact path='/dashboard' element={<PrivateRoute Component={Dashboard} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>
          <Route exact path='/ListCategory' element={<PrivateRoute Component={ListCategory} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>
          <Route exact path='/AddCategory' element={<PrivateRoute Component={AddCategory} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>
          <Route exact path='/UpdateCategory/:id' element={<PrivateRoute Component={AddCategory} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>
          <Route exact path='/user-dashboard' element={<PrivateRoute Component={UserDashboard} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>
          <Route exact path='/error-page' element={<PrivateRoute Component={ErrorPage} isLoggedIn={localStorage.getItem("IsloggedIn")} />}/>

        </Routes>
       </Router>
  );
}

const PrivateRoute = ({ Component }) => {
  const isLoggedIn = localStorage.getItem('IsLoggedIn')
  return isLoggedIn ? <Component /> : <Navigate to="/" replace />;
}

export default App;
