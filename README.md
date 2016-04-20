# Group-21-Project
Group 21 Project
Ashley, Brian, Serah, Leo, Jeff

To handle the coding challenge of adding a graphical representation of data to our device, we opted to use the AndroidPlot library.  With this, we're able to plot a series of coordinates on an XY plane.  What we're trying to do is store (day, score) data points over the course of a week, using the day for the x-axis and score for the y-axis.  We take values that have been stored on the device from earlier sessions, and plot those points.  The libraries we tried to use were android's GraphView, using AChartEngine, MPAndroidChart, and finally AndroidPlot.  Across all of these, trying to synthesize the information to construct a full-on graph that fit into the screen proved to be nontrivial, however with AndroidPlot we found its approach to be the most intuitive.  Although there were various methods that we needed to conduct some research on to fully utilize them, it was able to be done in reasonable time.

To make the graph more aesthetically pleasing, however, what needs to be done is to be able to customize the backdrop of the plot, and also of the points displayed, so that the design is more in-line with our color scheme as opposed to looking like a full-on business meeting graph.
