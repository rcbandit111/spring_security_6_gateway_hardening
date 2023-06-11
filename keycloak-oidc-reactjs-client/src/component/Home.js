import React, { Component } from 'react';
import Keycloak from 'keycloak-js';
import { Config, CookieName } from '../model/Config';
import { SetCookieValue, parseToken } from '../model/Functions';

let keycloak;
class Home extends Component{
    constructor(props){
        super(props);
        keycloak = new Keycloak(Config);
    }
    componentDidMount(){
        console.log('keycloak home componentDidMount');
        keycloak.init({ flow: 'implicit', checkLoginIframe: false }).success(function (authenticated) {
            console.log('keycloak authenticated =', keycloak.authenticated, ',status=', authenticated)
            if (keycloak.authenticated) {
                parseToken(keycloak.token);
                sessionStorage.setItem("authKey", keycloak.token);
                SetCookieValue(CookieName, keycloak.idTokenParsed.name);
                window.location.href = window.location.origin + "/auth";
            }
        }).then(() => {
            return false;
        })
        .catch((error) => {
            console.log("Something went wrong due to \n" + error);
            sessionStorage.setItem("authKey", '');
            window.location.href = window.location.origin + "/";
        })
    }
    authenticateLogin = () => {
        keycloak.login({ redirectUri: window.location.origin })
    }
    render(){
        return(
            <div className="mdWrapper">
                <img src="./image/logo.png" alt="Keyclock OIDC" />
                <h3>Keycloak - OIDC</h3>
                <h4>React JS - Implicit flow authentication</h4>
                <button className="btn-normal" onClick={this.authenticateLogin}>Login</button>
            </div>
        )
    }
}

export default Home;
