import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import LoginForm from './Components/Authorization/LoginForm';
import RegistrationForm from './Components/Authorization/RegistrationForm';
import Dashboard from './Components/Pages/Dashboard';

function App() {
  return (
     <Router>
        <Routes>
          <Route exact path='/' Component={ LoginForm}></Route>
          <Route exact path='/register' Component={ RegistrationForm}></Route>
          <Route exact path='/dashboard' Component={ Dashboard}></Route>
        </Routes>
       </Router>
  );
}

export default App;
