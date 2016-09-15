(ns carafe.websockets)

(defn create-websocket
  "Create a websocket and provide all of it's callbacks

  :onopen Event
  :onerror Event
  :onclose CloseEvent
  :onmessage MessageEvent"
  [url {:keys [onopen onerror onclose onmessage]}]
  (let [ws (js/WebSocket. url)]
    (set! (.-onopen ws) onopen)
    (set! (.-onerror ws) onerror)
    (set! (.-onclose ws) onclose)
    (set! (.-onmessage ws) onmessage)
    ws))
