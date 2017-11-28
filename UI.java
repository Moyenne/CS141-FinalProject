	private void doMoveAction() {
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.moveUp())
			dirOptions.add("up");
		if(engine.moveLeft())
			dirOptions.add("left");
		if(engine.moveDown())
			dirOptions.add("down");
		if(engine.moveRight())
			dirOptions.add("right");
		
		if(dirOptions.size() == 0)
		{
			engine.killPlayer();
			System.out.println("You have been surrounded! Try again, agent.");
		}
		else
		{
			System.out.println("Please enter the direction you would like to move:");
			displayOptions(dirOptions);
			input = getInput(dirOptions);
			
			engine.move(input);
		}
	}
