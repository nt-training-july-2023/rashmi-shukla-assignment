import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import LoginForm from './Pages/Authentication/LoginForm';
import RegistrationForm from './Pages/Authentication/RegistrationForm';
import Dashboard from './Pages/Dashboard/Dashboard';
import ListCategory from './Pages/Category/ListCategory';
import AddCategory from './Pages/Category/AddCategory';
import UserDashboard from './Pages/Dashboard/UserDashboard';
// import Privateroute from './Components/Privateroute';

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
        </Routes>
       </Router>
  );
}

export default App;
