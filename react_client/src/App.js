import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import Secured from "./pages/Secured";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        {/*<p>*/}
        {/*  Edit <code>src/App.js</code> and save to reload.*/}
        {/*</p>*/}
        <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
        >
          Login to Keycloak's default login page 2
        </a>
      </header>

        <Routes>
            <Route path="/secured" element={<Secured />} />
        </Routes>
    </div>
  );
}

export default App;
