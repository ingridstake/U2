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
  desktop1: {
    breakpoint: { max: 3000, min: 2012 },
    items: 4,
    paritialVisibilityGutter: 60
  },
  desktop2: {
    breakpoint: { max: 2012, min: 1024 },
    items: 3,
    paritialVisibilityGutter: 60
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 2,
    paritialVisibilityGutter: 50
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1,
    paritialVisibilityGutter: 30
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
                    <Card.Body className='c-body'>
                        <Card.Img variant="top" src={e.imageUrl} />
                        <Card.Title className='c-title'><h1>{e.name}</h1> </Card.Title>
                    </Card.Body>
                    
                    <Card.Footer className='c-footer'>
                        <Button>Köp</Button>
                        <Card.Text><p>{e.date}, {e.city}</p></Card.Text>
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
