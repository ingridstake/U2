//skapa en en rad i lisatan med namn

//import { Button, Card } from "react-bootstrap";
import { Card } from "react-bootstrap";
import { event } from "./Models";
import '../styles/searchResult.css';

export const SearchItem = (e: event) => {
    return (
        <Card className="search-result">
            <Card.Link href={e.infoUri}>
                <Card.Text>
                    <div className="row">
                        <p className="search-result-name">{e.name} </p>
                        <p className="search-result-date">{e.date}</p>
                    </div>
                    <p className="search-result-city">{e.city}</p>
                </Card.Text>
            </Card.Link>
        </Card>
    )
}
