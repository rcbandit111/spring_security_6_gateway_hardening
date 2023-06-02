import React from "react";
import {
  Route,
  Routes,
  Outlet,
  Navigate,
  useLocation,
  BrowserRouter as Router,
} from "react-router-dom";
import { getCurrentUser } from "./model/Functions";
import Home from "./component/Home";
import AuthorisedPage from "./component/AuthorisedPage";

const PrivateRoute = () => {
  const location = useLocation();

  return getCurrentUser() ? (
    <Outlet />
  ) : (
    <Navigate
      to={{
        pathname: "/",
        state: { from: location },
      }}
    />
  );
};

const NonPrivateRoute = () => {
  const location = useLocation();

  return getCurrentUser() ? (
    <Navigate
      to={{
        pathname: "/auth",
        state: { from: location },
      }}
    />
  ) : (
    <Outlet />
  );
};

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<NonPrivateRoute />}>
          <Route index element={<Home />} />
        </Route>
        <Route path="/auth" element={<PrivateRoute />}>
          <Route index element={<AuthorisedPage />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
