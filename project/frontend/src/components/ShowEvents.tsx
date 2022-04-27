import React, { useEffect, useState } from 'react';
import '../styles/showEvents.css';
import Table from "react-bootstrap/Table";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import CardGroup from "react-bootstrap/CardGroup";
import axios from 'axios';


export default function JsonDataDisplay() {

    const [appState, setAppState] = useState({
        loading: false,
        events: [] as any[]
    });

    useEffect(() => {
        setAppState({loading: true, events: []});
        const apiUrl = 'http://127.0.0.1:8080/ten_events_string';
        axios.get(apiUrl).then(res => {
            const allEvents = res.data
            setAppState({ loading: false, events: allEvents });
        })
    }, [setAppState])

   return(
        <CardGroup className="card-grid">
            {appState.events.length > 0 && appState.events.map(e => (
                <Row xs={1} md={2}>
                    {Array.from({ length: 1 }).map((_, index) => (
                        <Col>
                            <Card  key={e.id}>
                                <Card.Img variant="top" src="holder.js/100px160" />
                                <Card.Body>
                                    <Card.Title><h1>titel:{e.name}</h1> </Card.Title>
                                    <Card.Text><p>Eventet äger rum i {e.city}</p></Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            ))}
        </CardGroup>
   )
 }
