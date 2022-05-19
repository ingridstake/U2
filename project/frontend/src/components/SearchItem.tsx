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
                <ListGroup className="searchResult">
            <ListGroup.Item className="searchResultItem">{e.name}</ListGroup.Item>
        </ListGroup>

         <div className="searchResult">
            <p className="searchResultItem">{e.name}</p>
        </div>

        <p className="searchResultItem">{e.name}</p>
        */
    return (
        <a href={e.infoUri}>
            <ListGroup className="searchResult" horizontal>
                <ListGroup.Item className="search-result-name">{e.name}</ListGroup.Item>
                <ListGroup.Item className="search-result-city"> {e.city}</ListGroup.Item>
            </ListGroup>
        </a>
    )
}
