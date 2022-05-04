import { Button, Card } from "react-bootstrap";
import { event } from "./Models";

/**
 * An event card that includes the event's image, date and city, and a "buy" button.
 * @param e is an event of type event
 * @returns an event card
 */
export const EventCard = (e: event) => {
    return (
    <Card>
        <Card.Body className='c-body'>
            <Card.Img variant="top" src={e.imageUrl} />
            <Card.Title className='c-title'><h1>{e.name}</h1> </Card.Title>
        </Card.Body>

        <Card.Footer className='c-footer'>
            <Button>Köp</Button>
            <Card.Text><p>{e.date}, {e.city}</p></Card.Text>
        </Card.Footer>
    </Card>
    )
}