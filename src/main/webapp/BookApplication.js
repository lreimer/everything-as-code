class Book extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="book">
                <h2 className="book-title">
                    {this.props.book.title}
                </h2>
                <p className="book-author">
                    {this.props.book.author}
                </p>
                <p className="book-isbn">
                    {this.props.book.isbn}
                </p>
            </div>
        );
    }
}

class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {searchIsbnInput: '', searchTitleInput: ''};
        //Bindings of the methods
        this.handleIsbnSearch = this.handleIsbnSearch.bind(this);
        this.handleTitleSearch = this.handleTitleSearch.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleIsbnSearch(event) {
        this.setState({searchIsbnInput: event.target.value});
    }

    handleTitleSearch(event) {
        this.setState({searchTitleInput: event.target.value});
    }

    handleSubmit(e) {
        //Prevent the browser's default action of submitting the form
        e.preventDefault();

        var searchIsbnInput = this.state.searchIsbnInput.trim();
        var searchTitleInput = this.state.searchTitleInput.trim();
        //Call the passed callback functions
        if (searchIsbnInput.length > 0 && searchTitleInput.length > 0) {
            alert('Sorry, but you cannot search for both at the same time.');
        }
        else if (searchIsbnInput.length > 0) {
            this.props.onIsbnSearch({searchIsbnInput: searchIsbnInput});
        }
        else if (searchTitleInput.length > 0) {
            this.props.onTitleSearch({searchTitleInput: searchTitleInput});
        }
    }

    render() {
        return (
            <div className="book-search">
                <input
                    type="search"
                    value={this.state.searchIsbnInput}
                    onChange={this.handleIsbnSearch}
                    placeholder="Search for isbn"
                    className="search-field"
                />
                <input
                    type="search"
                    value={this.state.searchTitleInput}
                    onChange={this.handleTitleSearch}
                    placeholder="Search for title"
                    className="search-field"
                />
                <button onClick={this.handleSubmit} className="search-button">Search</button>
            </div>
        );
    }
}

const BOOK_PATH = '/api/books';

class BookApplication extends React.Component {

    constructor() {
        super();
        this.state = {books: []};
        this.handleIsbnSearch = this.handleIsbnSearch.bind(this)
        this.handleTitleSearch = this.handleTitleSearch.bind(this)
    }

    /**
     * Make a REST call to load the books when the component is ready
     */
    componentDidMount() {
        $.get(BOOK_PATH, function (result) {
            this.setState({
                books: result
            });
        }.bind(this));
    }

    /**
     * Make a REST call for the book with the given isbn
     * @param givenInput the isbn to search for
     */
    handleIsbnSearch(givenInput) {
        var path = BOOK_PATH + '/' + givenInput.searchIsbnInput;
        let self = this;
        $.get(path, function (result) {
            let filteredBook = [];
            filteredBook.push(result);
            self.setState({
                books: filteredBook
            });
        });
    }

    /**
     * Make a REST call for the book with the given title
     * @param givenInput the title to search for
     */
    handleTitleSearch(givenInput) {
        var path = BOOK_PATH + '?title=' + givenInput.searchTitleInput;
        let self = this;
        $.get(path, function (result) {
            self.setState({
                books: result
            });
        });
    }

    /**
     * The rendering method, showing the books.
     * @returns all books
     */
    render() {
        return (
            <div>
                <SearchBox onIsbnSearch={this.handleIsbnSearch} onTitleSearch={this.handleTitleSearch}/>
                <div className="book-list">
                    {this.state.books.map(function (currentBook) {
                        return <Book book={currentBook} key={currentBook.id}/>;
                    }) }
                </div>
            </div>
        );
    }

}

//Instantiate the root component and start the framework
ReactDOM.render(<BookApplication />, document.getElementById('content'));