import React from "react";
import { Container } from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import './style/header_footer.css';
import tickster_logo from './img/logo_tickster_color.svg';

function Header() {
    return (
        <>
        <Navbar className="header" >
          <Container>
            <Navbar.Brand href="#Home">
                <img src={tickster_logo}  />
            </Navbar.Brand>
          </Container>
        </Navbar>
        </>
    );
}

export default Header;