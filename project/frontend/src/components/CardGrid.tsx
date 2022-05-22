import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import CardGroup from "react-bootstrap/CardGroup";
import '../styles/showEvents.css';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';
import { EventCards } from "./EventCards";
import { EventCard } from "./EventCard";




export default function CardGrid() {

    const [appState, setAppState] = useState({
        loading: false,
        events: [] as any[]
    });

    useEffect(() => {
        setAppState({loading: true, events: []});
        const apiUrl = 'http://localhost:8080/ten_events_string';
        axios.get(apiUrl).then(res => {
            const allEvents = res.data
            setAppState({ loading: false, events: allEvents });
        })
    }, [setAppState])
    return(
        <CardGroup className="grid">
            {appState.events.length > 0 && appState.events.map(e => (
                <Row xs={1} md={2}>
                    {Array.from({ length: 1 }).map((_, index) => (
                        <Col>
                            <Card className="c-card cc-card" >
                                <Card.Body>
                                    <Card.Img variant="top" src={e.imageUrl} />
                                    <Card.Link href={e.infoUri}>
                                        <Card.Title><h5>{e.name}</h5> </Card.Title>
                                    </Card.Link>            
                                </Card.Body>

                                <Card.Footer>
                                    <Card.Link href={e.shopUri}>
                                        <Button>Köp</Button>
                                    </Card.Link>
                                    <Card.Text>{e.date}, {e.venue}</Card.Text>
                                </Card.Footer>
                            </Card>
                        </Col>
                    ))}
                </Row>
            ))}
        </CardGroup>
    )
}