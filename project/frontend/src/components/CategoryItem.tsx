import Carousel from 'react-multi-carousel';
/*import 'react-multi-carousel/lib/styles.css'*/
import { ListGroupItem } from 'react-bootstrap';
import '../styles/showEventCat.css';
import { EventCards} from './EventCards';
import { CategoryTagButtons } from './CategoryTagButtons';
import { category, event } from './Models'
import { Component, Key } from 'react';

type eventsToShow = {
  events : JSX.Element[];
}
/**
 * Class component that is a Category item.
 * Renders a ListGroupItem consisting of category title, category tags and EventCard that belongs to the category.
 * Takes a category as parameter.
 */
export class CategoryItem extends Component <category,eventsToShow> implements JSX.Element {
  type: any;
  key: Key | null = null;
  state: eventsToShow = {events: []}
  allEvents: category = {events: [], category: '', tags: []}

  checkedTags: string[] = [];

  constructor(props: category) {
    super(props)
    console.log(props)
    this.allEvents = {events: this.props.events, category: this.props.category, tags: this.props.tags}
    this.state = {events: EventCards(this.props.events)}
    this.updateEventsToShow = this.updateEventsToShow.bind(this);
  }

  updateEventsToShow(tagName: string){
    if(!this.checkedTags.includes(tagName)) {       // check if tag is choosed before or not, if it isn't; it should be added to checkTags
      this.checkedTags.push(tagName)
    }
    else {                                          // else; it should be removed
      this.checkedTags = this.checkedTags.filter(t => t !== tagName);
    }
    
    if(this.checkedTags.length === 0) {             // if there isn't any tags selected, all events should show
      this.state.events = EventCards(this.allEvents.events);
    }
    else {                                          // else, we only want to show the events with the selected tag
      var filteredEvents = this.allEvents.events?.filter((e: event) => this.checkedTags?.some((t: string) => e.e_tags.includes(t)));
      this.state.events = EventCards(filteredEvents);
    }
    this.forceUpdate();
  }

  render() {
    return (
      <ListGroupItem>
      <div className="category-title">
        <h1 className="cat-title">{this.allEvents.category}</h1>
        <div className='cat-tags'>{CategoryTagButtons(this.allEvents.tags, this.updateEventsToShow)}</div>
      </div>
      
      <Carousel 
      responsive={responsive}
      showDots={true}
      /*infinite={true}*/
      shouldResetAutoplay={false}
      customTransition="1000ms cubic-bezier(0.645, 0.045, 0.355, 1) 0s"
      >
        {this.state.events}

      </Carousel>
      </ListGroupItem>
    )
  };

}

/**
     * Responsive item settings for different platform sizes.
     * Used in Carousel.
     */
 const responsive = {
    desktopBig: {
    breakpoint: { max: 3000, min: 1550 },
    items: 4,
    slidesToSlide: 4,
    },
    desktopSmall: {
    breakpoint: { max: 1550, min: 1048 },
    items: 3,
    slidesToSlide: 3,
    },
    tablet: {
    breakpoint: { max: 1048, min: 576 },
    items: 2,
    slidesToSlide: 2,
    },
    mobile: {
    breakpoint: { max: 576, min: 0 },
    items: 1,
    slidesToSlide: 1,
    }
};


  