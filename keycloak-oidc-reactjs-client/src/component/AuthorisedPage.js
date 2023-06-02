import React, { Component } from "react";
import { CookieName } from "../model/Config";
import { GetCookieValue } from "../model/Functions";
import axios from "axios";

class AuthorisedPage extends Component {
  constructor() {
    super();
    this.state = {
      username: GetCookieValue(CookieName),
      data: [],
    };
  }
  componentDidMount() {
    const parts = this.state.username.split(" ");
    const token = parts[parts.length - 1];

    axios
      .get("/api/microservice/dashboard/test", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
      .then((res) => {
        console.log(res.data);
      });
  }
  render() {
    let { username } = this.state;
    return (
      <div className="mdWrapper">
        <img src="./image/logo.png" alt="Keycloak OIDC" />
        <h3>Keycloak - OIDC</h3>
        <h4>React JS - Implicit flow authentication</h4>
        {username ? (
          <p>
            Welcome <strong>{username}</strong>
          </p>
        ) : (
          ""
        )}
      </div>
    );
  }
}

export default AuthorisedPage;
