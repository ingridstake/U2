import React from "react";
import { Container } from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import './style/headerFooter.css';
import ticksterLogo from '../Resources/img/logo_tickster_color.svg';

function Header() {
    return (
        <>
        <Navbar className="header" >
          <Container>
            <Navbar.Brand href="#Home">
                <img alt="Tickster logo" src={ticksterLogo}/>
            </Navbar.Brand>
          </Container>
        </Navbar>
        </>
    );
}

export default Header;