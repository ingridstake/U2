//skapa en en rad i lisatan med namn

//import { Button, Card } from "react-bootstrap";
import { Card, ListGroup } from "react-bootstrap";
import { event } from "./Models";
import '../styles/searchResult.css';

export const SearchItem = (e: event) => {
    return (
        <Card className="searchResult">
            <Card.Link href={e.infoUri}>
                <Card.Text className="row">
                    <div className="column"> 
                        <p className="search-result-name">{e.name} </p> 
                    </div>
                    <div className="column"> 
                        <p className="search-result-location"> {e.venue}</p>
                    </div>
                </Card.Text>
                <Card.Text> <p className="search-result-date">{e.date}</p>   </Card.Text>
            </Card.Link>
        </Card>
    )
}
