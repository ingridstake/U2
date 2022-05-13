//skapa en en rad i lisatan med namn

//import { Button, Card } from "react-bootstrap";
import { ListGroup } from "react-bootstrap";
import { event } from "./Models";

export const SearchItem = (e: event) => {
    return (
        <div>
            <p>
                {e.name}
            </p>
        </div>

    )
}