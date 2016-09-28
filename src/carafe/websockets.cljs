(ns carafe.websockets)

(defn create-websocket
  "Create a websocket and provide all of it's callbacks

  :onopen :: ws -> Event -> ()
  :onerror :: ws -> Event -> ()
  :onclose :: ws -> CloseEvent -> ()
  :onmessage :: ws -> MessageEvent -> ()"
  [url {:keys [onopen onerror onclose onmessage]}]
  (let [ws (js/WebSocket. url)]
    (set! (.-onopen ws) (partial onopen ws))
    (set! (.-onerror ws) (partial onerror ws))
    (set! (.-onclose ws) (partial onclose ws))
    (set! (.-onmessage ws) (partial onmessage ws))
    ws))
