import Carousel from 'react-multi-carousel';
//import { Image } from "semantic-ui-react";
import { Image, ListGroup, ListGroupItem } from 'react-bootstrap';
import JsonDataDisplay from './ShowEvents';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import '../styles/showEvents.css';
import '../styles/showEventCat.css';


const responsive = {
  desktopBig: {
    breakpoint: { max: 3000, min: 1200 },
    items: 4,
  },
  desktopSmall: {
    breakpoint: { max: 1200, min: 768 },
    items: 3,
  },
  tablet: {
    breakpoint: { max: 767, min: 576 },
    items: 2,
  },
  mobile: {
    breakpoint: { max: 575, min: 0 },
    items: 1,
  }
};

/**
 * 
 * @returns a list of categories with event cards
 */
export default function DataCat() {
  const [appState, setAppState] = useState({
    loading: false,
    cat: [] as any[]
  });

  useEffect(() => {
      setAppState({loading: true, cat: []});
      const apiUrl = 'http://127.0.0.1:8080/vowel_events';
      axios.get(apiUrl).then(res => {
          const allCategories = res.data
          setAppState({ loading: false, cat: allCategories });  
      })
  }, [setAppState])

  return (
    <ListGroup>
      {appState.cat.map(c => (
        <ListGroupItem><h1 className="cat-title">{c.category}</h1>
          <Carousel
            //ssr
            //partialVisbile
            /*deviceType={deviceType}*/
            //itemClass="image-item"
            responsive={responsive} 
          >
          {c.events.map((e: { imageUrl: string; name: string; date: string; city: string; }) => {
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
              );
            })}
          </Carousel>
        </ListGroupItem>
        ))}
    </ListGroup>
  );
}


// Because this is an inframe, so the SSR mode doesn't not do well here.
// It will work on real devices.
