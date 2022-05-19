//skapa en en rad i lisatan med namn

//import { Button, Card } from "react-bootstrap";
import { ListGroup } from "react-bootstrap";
import { event } from "./Models";
import '../styles/searchResult.css';

export const SearchItem = (e: event) => {

    /*
    <div>
            <p>
                {e.name}
            </p>
        </div>

         <div className="searchResult">
            <p className="searchResultItem">{e.name}</p>
        </div>
        */
    return (
        <ListGroup className="searchResult">
            <ListGroup.Item className="searchResultItem">{e.name}</ListGroup.Item>
        </ListGroup>

    )
}
