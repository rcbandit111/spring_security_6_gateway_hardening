import React, { Component } from "react";
import { CookieName } from "../model/Config";
import { GetCookieValue } from "../model/Functions";
import axios from "axios";

class AuthorisedPage extends Component {
  constructor() {
    super();
    this.state = {
      username: GetCookieValue(CookieName),
      data: '',
    };
  }
  componentDidMount() {
    const token = sessionStorage.getItem("authKey");
    console.log('token =', token)

    axios
        .get("https://api2.hireya.org/api/microservice/dashboard/test_sec", {
          headers: {
            Authorization: "Bearer " + token,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.setState({
            data: res.data.msg,
          })
        }).catch((error) => {
          console.log("Something went wrong due to \n " + error);
          sessionStorage.setItem("authKey", '');
          window.location.href = window.location.origin + "/";
    });

  }
  handleLogout = () => {
    sessionStorage.setItem("authKey", '');
    window.location.href = window.location.origin + "/";
  }
  render() {
    let { username } = this.state;
    return (
      <div className="mdWrapper">
        <img src="./image/logo.png" alt="Keycloak OIDC" />
        <h3>Keycloak - OIDC</h3>
        <h4>React JS - Implicit flow authentication</h4>
        {username ? (
          <div>
          <p>
            Welcome <strong>{username}</strong>
          </p>
          <p>
            The information from dashboard is <strong>{this.state.data}</strong>
          </p>
          <p>
          <button className="btn-normal" onClick={this.handleLogout}>Logout</button>
          </p>
          </div>
        ) : (
          "User Not Logined"
        )}
      </div>
    );
  }
}

export default AuthorisedPage;
