import React, { useEffect, useState } from 'react';
import '../styles/showEvents.css';
import Table from "react-bootstrap/Table";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import CardGroup from "react-bootstrap/CardGroup";
import axios from 'axios';
import { Button } from 'react-bootstrap';


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
                                <Card.Body className='c-body'>
                                    <Card.Img variant="top" src={e.imageUrl} />
                                    <Card.Title className='c-title'><h1>{e.name}</h1> </Card.Title>
                                </Card.Body>
                                
                                <Card.Footer className='c-footer'>
                                    <Button>Köp</Button>
                                    <Card.Text><p>{e.date}, {e.city}</p></Card.Text>
                                </Card.Footer>
                            </Card>
                        </Col>
                    ))}
                </Row>
            ))}
        </CardGroup>
   )
 }
