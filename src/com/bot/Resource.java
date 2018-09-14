package com.bot;

import com.utils.MathUtils;

public class Resource
{
    private String resourceName;

    private int minResource;
    private int maxResource;

    protected int resource;

    public Resource(final String resourceName, final int minResource, final int maxResource)
    {
        this(resourceName, minResource, maxResource, 0);
    }

    public Resource(final String resourceName, final int minResource, final int maxResource, final int resource)
    {
        if (maxResource <= minResource)
        {
            try
            {
                throw new Exception("The variable 'maxResource' cannot be greater than 'minResource'.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        this.resourceName = resourceName;

        this.minResource = minResource;
        this.maxResource = maxResource;
        this.resource = MathUtils.range(resource, minResource, maxResource);
    }

    private final int produceLaw(final int count) { return resource + count; }
    private final int consumeLaw(final int count) { return resource - count; }

    protected boolean canConsume(final int count)
    {
        return count > 0 && consumeLaw(count) >= getMinResource();
    }
    protected boolean canProduce(final int count)
    {
        return count > 0 && produceLaw(count) <= getMaxResource();
    }

    /* This parts '>= ...' or '<= ...' do nothing useful in 'consume' and 'produce' functions!
     *
     * Equivalent:
     * public boolean consume(final int count)
     * {
     *      if (canConsume(count))
     *      {
     *          resource = consumeLaw(count);
     *
     *          return true;
     *      }
     *
     *      return false;
     * }
     */
    public boolean consume(final int count)
    {
        return canConsume(count) && (resource = consumeLaw(count)) >= minResource;
    }

    public boolean produce(final int count)
    {
        return canProduce(count) && (resource = produceLaw(count)) <= maxResource;
    }

    public boolean transfer(final int count, final Resource destination)
    {
        if (canConsume(count) && destination.canProduce(count))
        {
            this.consume(count);
            destination.produce(count);

            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Count of " + resourceName + " is " + resource + "/" + maxResource + ".";
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public int getResourceCount()
    {
        return resource;
    }

    public int getMinResource()
    {
        return minResource;
    }

    public int getMaxResource()
    {
        return maxResource;
    }
}
