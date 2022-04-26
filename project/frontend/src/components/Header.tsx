import React from "react";
import { Container } from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import '../styles/headerFooter.css';
import ticksterLogo from '../resources/images/logo_tickster_white.svg';

function Header() {
    return (
        <>
        <Navbar className="header" >
            <Navbar.Brand href="#home" className="logo">
                <img alt="Tickster logo" src={ticksterLogo}/>
            </Navbar.Brand>
        </Navbar>
        </>
    );
}

export default Header;