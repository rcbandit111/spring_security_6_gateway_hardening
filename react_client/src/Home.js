import logo from './logo.svg';
import './App.css';
import {Link} from "react-router-dom";

function Home() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
        >
          Login to Keycloak's default login page
        </a>
      </header>

        <Link to="/secured">Secured</Link>
    </div>
  );
}

export default Home;
