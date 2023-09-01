import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import LoginForm from './Components/Authentication/LoginForm';
import RegistrationForm from './Components/Authentication/RegistrationForm';
import Dashboard from './Components/Pages/Dashboard';
import ListCategory from './Components/Pages/Category/ListCategory';
import AddCategory from './Components/Pages/Category/AddCategory';
import UserDashboard from './Components/UserDashboard';
import Privateroute from './Components/Privateroute';
import AdminDashboard from './Components/AdminDashboard';

function App() {
  return (
     <Router>
        <Routes>
          <Route exact path='/' Component={ LoginForm}></Route>
          <Route exact path='/register' Component={ RegistrationForm}></Route>
          <Route exact path='/dashboard' Component={ Dashboard}></Route>
          <Route exact path='/ListCategory' Component={ ListCategory}></Route>
          <Route exact path='/AddCategory' Component={AddCategory}></Route>
          <Route exact path='/UpdateCategory/:id' Component={AddCategory}></Route>
          {/* <Route exact path='/private' Component={Privateroute}> */}
            <Route exact path='/user-dashboard' Component={UserDashboard}></Route>
          {/* </Route> */}
          <Route exact path='admin-dashboard' Component={AdminDashboard}></Route>
        </Routes>
       </Router>
  );
}

export default App;
