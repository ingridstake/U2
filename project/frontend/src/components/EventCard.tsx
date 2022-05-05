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
        <Card.Body>
            <Card.Img variant="top" src={e.imageUrl} />
            <Card.Title><h5>{e.name}</h5> </Card.Title>
        </Card.Body>

        <Card.Footer>
            <Button>Köp</Button>
            <Card.Text>{e.date}, {e.city}</Card.Text>
        </Card.Footer>
    </Card>
    )
}