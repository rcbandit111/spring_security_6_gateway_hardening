import React from 'react';
import {BrowserRouter as Router, Navigate, Route} from 'react-router-dom';
import { getCurrentUser } from './model/Functions';
import Home from './component/Home';
import AuthorisedPage from './component/AuthorisedPage';

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
    getCurrentUser() ? (
      <Component {...props} />
    ) : (
        <Navigate to={{
          pathname: '/',
          state: { from: props.location }
        }} />
      )
  )} />
)
const NonPrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={props => (
    getCurrentUser() ? (
      <Navigate to={{
        pathname: "/auth",
        state: { from: props.location }
      }} />
    ) : (
        <Component {...props} />
      )
  )} />
)

function App() {
  return (
    <Router>
      <NonPrivateRoute exact path="/" component={Home} />
      <PrivateRoute exact path="/auth" component={AuthorisedPage} />
    </Router>
  );
}

export default App;
