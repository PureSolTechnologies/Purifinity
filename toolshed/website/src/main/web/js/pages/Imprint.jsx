import React from 'react';

export default class Imprint extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
                <div className="row">
                <div className="col-md-12">
                    <h1>Imprint</h1>

                    <address>
                        <b>PureSol Technologies</b><br />
                        Rick-Rainer Ludwig<br />
                        Zum Heiderand 21<br />
                        01328 Dresden<br />
                        Germany<br />
                        <br />
                        Phone: +49 (0)162 42 44 195<br />
                        e-Mail: <a href="mailto:contact@puresol-technologies.com">contact@puresol-technologies.com</a><br />
                        Internet: <a href="http://www.puresol-technologies.com">http://www.puresol-technologies.com</a>
                    </address>
                    <p>
                        Responsable for content regarding § 6 MDStV, see above.
                  </p>

                    <h1>Responsibility Notice</h1>
                    <p>
                        Even with careful control we do not take responsibility for the content of external web pages. Responsible for the content of external pages are the operators of these.
                  </p>

                    <h1>Disclaimer</h1>
                    <p>
                        The provided information on our pages was checked carefully and will be updated regularily. But we do not warrant that all information is always correct at all times. We might extent, remove or change any information without further notice.
                  </p>

                    <h1>Data Privacy Notice</h1>
                    <p>
                        The usage of our web pages is possible without any publication of your identity or private data. Regarding the usage of the web pages, the server might log some information which might make it possible to get private information like IP address, date and time and pages viewed. We will not analyse the data for personal analysises. We might use the data which was made anonymous for statisical analysis.
                  </p>
                </div>
            </div>

        );
    }
}
