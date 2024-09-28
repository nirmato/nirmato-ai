module.exports = function(config) {
    config.set({
        client: {
            mocha: {
                timeout: '300s'
            }
        },
        browserNoActivityTimeout: 300000,
        browserDisconnectTimeout: 300000
    });
}
