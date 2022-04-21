import React from "react";
import { Container } from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import '../styles/headerFooter.css';
import ticksterLogo from '../resources/images/logo_tickster_color.svg';

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